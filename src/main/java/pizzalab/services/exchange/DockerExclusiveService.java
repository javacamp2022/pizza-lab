package pizzalab.services.exchange;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile("docker")
public class DockerExclusiveService {

  @PostConstruct
  public void postContruct() {
    System.out.println("DockerExclusiveService started - only visible with running with docker profile");
  }
}
