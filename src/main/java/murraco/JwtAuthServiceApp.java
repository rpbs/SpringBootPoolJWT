package murraco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import murraco.dto.OptionsDTO;
import murraco.dto.PoolDTO;
import murraco.model.Options;
import murraco.model.Pool;
import murraco.service.OptionService;
import murraco.service.PoolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import murraco.model.Role;
import murraco.model.User;
import murraco.service.UserService;

@SpringBootApplication
public class JwtAuthServiceApp implements CommandLineRunner {

  @Autowired
  UserService userService;

  @Autowired
  PoolService poolService;

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... params) throws Exception {
    User admin = new User();
    admin.setUsername("admin");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

    userService.signup(admin);

    User client = new User();
    client.setUsername("client");
    client.setPassword("client");
    client.setEmail("client@email.com");
    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

    userService.signup(client);

    /*

    PoolDTO x = new PoolDTO();
    x.setId(1);
    x.setTitle("a");
    x.setDescription("a");
    x.setOptions( new ArrayList<OptionsDTO>() {
      {
        add(new OptionsDTO("Geeks"));
        add(new OptionsDTO("for"));
        add(new OptionsDTO("nice"));
      }
    });

    this.poolService.CreatePool(x);

    x = new PoolDTO();
    x.setId(1);
    x.setTitle("a");
    x.setDescription("a");
    x.setOptions( new ArrayList<OptionsDTO>() {
      {
        add(new OptionsDTO("kkk"));
        add(new OptionsDTO("xxx"));
        add(new OptionsDTO("www"));
      }
    });

    this.poolService.CreatePool(x);

   */
  }

}
