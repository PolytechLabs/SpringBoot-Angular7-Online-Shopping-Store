package me.zhulin.shopapi.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.zhulin.shopapi.entity.Cart;
import me.zhulin.shopapi.entity.ProductInOrder;
import me.zhulin.shopapi.entity.User;
import me.zhulin.shopapi.service.CartService;
import me.zhulin.shopapi.service.CategoryService;
import me.zhulin.shopapi.service.ProductService;
import me.zhulin.shopapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created By Zhu Lin on 1/2/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private UserService userService;

    @Mock
    private CartService cartService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void mergeCart403Test() throws Exception
    {
        String TOKEN_ATTR_NAME = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        User user = new User();
        user.setEmail("test@test.test");
        user.setActive(true);
        user.setId(11L);
        user.setPassword("sdafds");
        user.setName("aaaaa");
        user.setPhone("why");
        user.setAddress("help");

        Cart cart = new Cart();
        cart.setCartId(122L);
        cart.setUser(user);

        user.setCart(cart);



        ObjectMapper mapper = new ObjectMapper();

        // convert JSON string to Book object
        Collection<ProductInOrder> items = mapper.readValue(Paths.get("/Users/stevenpauljobs/Downloads/SpringBoot-Angular7-Online-Shopping-Store-master/backend/src/test/java/me/zhulin/resources/ItemsArray.json").toFile(), Collection.class);


        Mockito.when(userService.findOne("test@test.test")).thenReturn(user);
        Mockito.doNothing().when(cartService).mergeLocalCart(any(), any());

        this.mvc.perform(MockMvcRequestBuilders.post("/cart")
                .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                .param(csrfToken.getParameterName(), csrfToken.getToken())
                .content(asJsonString(items))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();

    }


    @Test
    public void modifyItem401Test() throws Exception
    {
        User user = new User();
        user.setEmail("test@test.test");
        user.setActive(true);
        user.setId(11L);
        user.setPassword("sdafds");
        user.setName("aaaaa");
        user.setPhone("why");
        user.setAddress("help");

        Cart cart = new Cart();
        cart.setCartId(122L);
        cart.setUser(user);

        user.setCart(cart);

        Mockito.when(userService.findOne("test@test.test")).thenReturn(user);
        Mockito.doNothing().when(cartService).mergeLocalCart(any(), any());

        this.mvc.perform(MockMvcRequestBuilders.put("/cart/122")
                .content(asJsonString(544))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE); //turn off everything
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY); // only use fields
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
