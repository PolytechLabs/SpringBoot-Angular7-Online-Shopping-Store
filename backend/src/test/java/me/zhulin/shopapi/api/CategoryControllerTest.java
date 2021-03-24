package me.zhulin.shopapi.api;

import me.zhulin.shopapi.entity.ProductCategory;
import me.zhulin.shopapi.entity.ProductInfo;
import me.zhulin.shopapi.service.CategoryService;
import me.zhulin.shopapi.service.ProductService;
import me.zhulin.shopapi.vo.response.CategoryPage;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest
{

    @Autowired
    private MockMvc mvc;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductService productService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void showOne200Test() throws Exception
    {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(1111);


        Page<ProductInfo> productInCategory = new Page<ProductInfo>()
        {
            @Override
            public int getTotalPages()
            {
                return 0;
            }

            @Override
            public long getTotalElements()
            {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super ProductInfo, ? extends U> function)
            {
                return null;
            }

            @Override
            public int getNumber()
            {
                return 0;
            }

            @Override
            public int getSize()
            {
                return 0;
            }

            @Override
            public int getNumberOfElements()
            {
                return 0;
            }

            @Override
            public List<ProductInfo> getContent()
            {
                return null;
            }

            @Override
            public boolean hasContent()
            {
                return false;
            }

            @Override
            public Sort getSort()
            {
                return null;
            }

            @Override
            public boolean isFirst()
            {
                return false;
            }

            @Override
            public boolean isLast()
            {
                return false;
            }

            @Override
            public boolean hasNext()
            {
                return false;
            }

            @Override
            public boolean hasPrevious()
            {
                return false;
            }

            @Override
            public Pageable nextPageable()
            {
                return null;
            }

            @Override
            public Pageable previousPageable()
            {
                return null;
            }

            @Override
            public Iterator<ProductInfo> iterator()
            {
                return null;
            }
        };

        PageRequest request = PageRequest.of(1, 1);

        var tmp = new CategoryPage("", productInCategory);
        tmp.setCategory("Books");


        Mockito.when(productService.findAllInCategory(1, request)).thenReturn(productInCategory);

        Mockito.when(categoryService.findByCategoryType(1)).thenReturn(productCategory);


        this.mvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void showOne404Test() throws Exception
    {
        this.mvc.perform(get("/categorys/1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

}
