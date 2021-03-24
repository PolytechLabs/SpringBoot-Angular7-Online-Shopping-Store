package me.zhulin.shopapi.api;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.zhulin.shopapi.entity.Cart;
import me.zhulin.shopapi.entity.OrderMain;
import me.zhulin.shopapi.entity.ProductInfo;
import me.zhulin.shopapi.entity.User;
import me.zhulin.shopapi.service.OrderService;
import me.zhulin.shopapi.service.ProductService;
import me.zhulin.shopapi.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.beans.PropertyEditor;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTest
{
 @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void finish200Test() throws Exception
    {

        OrderMain orderMain = new OrderMain();
        orderMain.setOrderId(1222L);
        orderMain.setBuyerName("Lucifer");
        orderMain.setBuyerEmail("test@test.test");
        orderMain.setOrderStatus(1);
        orderMain.setBuyerAddress("hell");
        orderMain.setBuyerPhone("666");
        orderMain.setOrderAmount(new BigDecimal(10.0));

        Mockito.when(orderService.findOne(1222L)).thenReturn(orderMain);

        this.mvc.perform(MockMvcRequestBuilders.patch("/order/finishOrder/1222")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
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
