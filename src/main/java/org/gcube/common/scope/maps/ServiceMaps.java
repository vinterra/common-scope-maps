package org.gcube.common.scope.maps;

import static org.gcube.common.scope.impl.ScopeBean.Type.VRE;

import java.util.Map;

import org.gcube.common.scope.api.ServiceMap;
import org.gcube.common.scope.impl.ScopeBean;

/** * 
 * <p>
 * At construction time, it configures itself with all the service maps found
 * in the classpath (excluding URLs available to primordial and extension
 * classloader). Recognises service maps as resources whose names match a
 * {@link ServiceMapScanner#mapConfigPattern}.
 * 
 * @author Fabio Simeoni
 * 
 */
public class ServiceMaps {

	private Map<String, ServiceMap> maps;

	
	
	public static ServiceMaps instance = new ServiceMaps();
	
	
	//helper
	public ServiceMap getMap(String context) {
		
		if (maps==null || maps.isEmpty())
			maps = ServiceMapScanner.maps();
		
		if (context==null)
			throw new IllegalStateException("current context is undefined");
		
		ScopeBean bean = new ScopeBean(context);
		
		if(bean.is(VRE))
			context = bean.enclosingScope().toString();

		ServiceMap map = maps.get(context);
		
		if (map==null)
			throw new IllegalStateException("a map for "+context+" is undefined");
		
		return map;
	}
	
}
