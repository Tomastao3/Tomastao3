import com.offbytow.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;

import java.net.URI;
import java.net.URISyntaxException;


public class JenkinsUtil {

    JenkinsServer jenkinsServer;

    public static void main(String[] args) {

    }

    public static JenkinsServer connection(String url) throws URISyntaxException {

        jenkinsServer = new JenkinsServer(new URI(url),"user","pwd");

        return jenkinsServer;
    }

    public static void triggerBuild(String env,String ms){
        JobWithDetails job = jenkinsServer.getJob(env + ms);
        job.build();
    }


    public static void triggerBuild(String env,String ms){
        JobWithDetails job = jenkinsServer.getJob(env + ms);
        Build lastBuild = job.getLastBuild();

        lastBuild.details().getResult();
        lastBuild.details().getTimestamp();

        job.getLastSuccessfulBuild().getNumber();
    }
}
