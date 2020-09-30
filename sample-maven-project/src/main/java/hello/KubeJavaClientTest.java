package hello;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Container;
import io.kubernetes.client.models.V1ObjectMeta;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodCondition;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.models.V1PodSpec;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;

public class KubeJavaClientTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    String kubeConfigPath = "C:\\Workplace\\TechPocWorkspace\\sample-maven-project\\KubeConfig";

	    ApiClient client;
		try {
			ApiCallbackResponse<V1Pod> apiCallback = new ApiCallbackResponse();
			//client = Config.defaultClient();
			 client =ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
			Configuration.setDefaultApiClient(client);

		    CoreV1Api api = new CoreV1Api();
		    V1PodList list =
		        api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
		    V1Pod oldPod = null;
		    for (V1Pod item : list.getItems()) {
		    	if(item.getMetadata().getName().contains("oldpod")) {
		    		oldPod = item;
		    		
		    	}
		        //System.out.println(item.getMetadata().getName());
		      }
			
		    V1Pod newPod = createPod(oldPod);
		    
		    System.out.println("Before creating Pod : "+newPod.getMetadata().getName());
		    api.createNamespacedPodAsync("my-namespace", newPod, false, null , null, apiCallback);
		    
		    System.out.println("Before Completion " + apiCallback.isComplete() + " : "+ apiCallback.isFailure());

		    while(apiCallback.isComplete() || apiCallback.isFailure()) {
		    	if(apiCallback.isComplete()) {
				    System.out.println("After creating Pod Success : "+newPod.getMetadata().getName());
		    	}
		    	if(apiCallback.isFailure()) {
				    System.out.println("After creating Pod Failure : "+newPod.getMetadata().getName());
		    	}
		    }
		    
		    Thread.sleep(60000);
		    V1PodList list2 =
			        api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
		    V1Pod newPodAfterCreate = null;
		    for (V1Pod item : list2.getItems()) {
		    	if(item.getMetadata().getName().equalsIgnoreCase("my-new-pod5")) {
		    		newPodAfterCreate = item;
		    		break;
		    	}
		      }
		    System.out.println("Status After Completion : "+newPodAfterCreate.getStatus().getConditions());

		    for(V1PodCondition condition : newPodAfterCreate.getStatus().getConditions()) {
			    System.out.println("Type : "+condition.getType());
			    System.out.println("Status : "+condition.getStatus());
		    	
		    }
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static V1Pod createPod(V1Pod oldPod) {
		V1ObjectMeta meta = new V1ObjectMeta();
		meta.name("my-new-pod5");

		/*V1EnvVar addr = new V1EnvVar();
		addr.name("var1");
		addr.value("value1");

		V1EnvVar port = new V1EnvVar();
		port.name("var2");
		port.value("value2");*/

		/*V1ResourceRequirements res = new V1ResourceRequirements();
		Map<String, String> limits = new HashMap<>();
		limits.put("cpu", "300m");
		limits.put("memory", "500Mi");
		res.limits(limits);*/

		V1Container container = new V1Container();
		container.name("my-name");
		container.image("my-image");
		//container.env(Arrays.asList(addr, port));
		//container.resources(oldPod.getSpec().getContainers().get(0).getResources());

		V1PodSpec spec = new V1PodSpec();
		spec.containers(Arrays.asList(container));

		V1Pod podBody = new V1Pod();
		podBody.apiVersion("v1");
		podBody.kind("Pod");
		podBody.metadata(meta);
		podBody.spec(spec);
		
		return podBody;
	}
	
	/*public long duration() {
		
	}*/
}
