@GrabResolver(name='apache-snapshot', root='http://repository.apache.org/snapshots/')
@Grab(group='org.apache.directory', module='groovyldap', version='0.1-SNAPSHOT')
import org.apache.directory.groovyldap.*;
import groovy.lang.Closure;
import javax.naming.directory.Attributes;
import javax.naming.directory.Attribute;
import javax.naming.NamingEnumeration;
import groovy.sql.Sql
import com.atlassian.jira.component.ComponentAccessor
import org.ofbiz.core.entity.DelegatorInterface
import org.ofbiz.core.entity.ConnectionFactory

def ldap = LDAP.newInstance('ldap://hostname:port', 'username', 'password')

List results = ldap.search(filter: 'User Object Filter', base: 'Base DN', scope: 'SUB')
log.warn( " ${results.size} entries found ".center(40,'-'))

def dn = results[0].getNameInNamespace();
Attributes attrs = results[0].getAttributes("");
NamingEnumeration<Attribute> en = attrs.getAll();
Map<String, Object> user_props_map = new LinkedHashMap<>();
map.put("dn", dn);
while (en.hasMore()) {
    Attribute attr = en.next();
    String key = attr.getID();
    user_props_map.put(key, attr.get(0).toString());
}
