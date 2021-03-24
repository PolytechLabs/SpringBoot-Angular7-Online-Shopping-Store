package me.zhulin.shopapi.api;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import me.zhulin.shopapi.entity.Cart;
import me.zhulin.shopapi.entity.ProductInfo;
import me.zhulin.shopapi.entity.User;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.WebApplicationContext;

import java.beans.PropertyEditor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static me.zhulin.shopapi.api.CartControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest
{

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void edit400Test() throws Exception
    {
        this.mvc.perform(MockMvcRequestBuilders.put("/seller/product/544/edit")
                .content(asJsonString(544))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void edit200Test() throws Exception
    {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1222");
        productInfo.setProductName("book");
        productInfo.setProductPrice(new BigDecimal(1.5));
        productInfo.setProductStock(1);

        productInfo.setCategoryType(1);

        Mockito.when(productService.findOne("1222")).thenReturn(productInfo);

        this.mvc.perform(MockMvcRequestBuilders.put("/seller/product/1222/edit")
                .content(this.asJsonString(productInfo))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void create200Test() throws Exception
    {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1222");
        productInfo.setProductName("book");
        productInfo.setProductPrice(new BigDecimal(1.5));
        productInfo.setProductStock(1);
        productInfo.setCategoryType(1);

        BindingResult bindingResult = new BindingResult()
        {
            @Override
            public Object getTarget()
            {
                return null;
            }

            @Override
            public Map<String, Object> getModel()
            {
                return null;
            }

            @Override
            public Object getRawFieldValue(String s)
            {
                return null;
            }

            @Override
            public PropertyEditor findEditor(String s, Class<?> aClass)
            {
                return null;
            }

            @Override
            public PropertyEditorRegistry getPropertyEditorRegistry()
            {
                return null;
            }

            @Override
            public String[] resolveMessageCodes(String s)
            {
                return new String[0];
            }

            @Override
            public String[] resolveMessageCodes(String s, String s1)
            {
                return new String[0];
            }

            @Override
            public void addError(ObjectError objectError)
            {

            }

            @Override
            public String getObjectName()
            {
                return null;
            }

            @Override
            public void setNestedPath(String s)
            {

            }

            @Override
            public String getNestedPath()
            {
                return null;
            }

            @Override
            public void pushNestedPath(String s)
            {

            }

            @Override
            public void popNestedPath() throws IllegalStateException
            {

            }

            @Override
            public void reject(String s)
            {

            }

            @Override
            public void reject(String s, String s1)
            {

            }

            @Override
            public void reject(String s, Object[] objects, String s1)
            {

            }

            @Override
            public void rejectValue(String s, String s1)
            {

            }

            @Override
            public void rejectValue(String s, String s1, String s2)
            {

            }

            @Override
            public void rejectValue(String s, String s1, Object[] objects, String s2)
            {

            }

            @Override
            public void addAllErrors(Errors errors)
            {

            }

            @Override
            public boolean hasErrors()
            {
                return false;
            }

            @Override
            public int getErrorCount()
            {
                return 0;
            }

            @Override
            public List<ObjectError> getAllErrors()
            {
                return null;
            }

            @Override
            public boolean hasGlobalErrors()
            {
                return false;
            }

            @Override
            public int getGlobalErrorCount()
            {
                return 0;
            }

            @Override
            public List<ObjectError> getGlobalErrors()
            {
                return null;
            }

            @Override
            public ObjectError getGlobalError()
            {
                return null;
            }

            @Override
            public boolean hasFieldErrors()
            {
                return false;
            }

            @Override
            public int getFieldErrorCount()
            {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors()
            {
                return null;
            }

            @Override
            public FieldError getFieldError()
            {
                return null;
            }

            @Override
            public boolean hasFieldErrors(String s)
            {
                return false;
            }

            @Override
            public int getFieldErrorCount(String s)
            {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors(String s)
            {
                return null;
            }

            @Override
            public FieldError getFieldError(String s)
            {
                return null;
            }

            @Override
            public Object getFieldValue(String s)
            {
                return null;
            }

            @Override
            public Class<?> getFieldType(String s)
            {
                return null;
            }
        };


        Mockito.when(productService.findOne("1222")).thenReturn(null);

        this.mvc.perform(MockMvcRequestBuilders.post("/seller/product/new")
                .content(this.asJsonString(productInfo))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void create405Test() throws Exception
    {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1222");
        productInfo.setProductName("book");
        productInfo.setProductPrice(new BigDecimal(1.5));
        productInfo.setProductStock(1);

        productInfo.setCategoryType(1);

        Mockito.when(productService.findOne("1222")).thenReturn(null);

        this.mvc.perform(MockMvcRequestBuilders.put("/seller/product/new")
                .content(this.asJsonString(productInfo))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();

    }


    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void delete200Test() throws Exception
    {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1222");
        productInfo.setProductName("book");
        productInfo.setProductPrice(new BigDecimal(1.5));
        productInfo.setProductStock(1);

        productInfo.setCategoryType(1);

        Mockito.when(productService.findOne("1222")).thenReturn(productInfo);

        this.mvc.perform(MockMvcRequestBuilders.delete("/seller/product/1222/delete")
                .content(this.asJsonString(1222))
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
