package me.zhulin.shopapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.zhulin.shopapi.entity.ProductCategory;
import me.zhulin.shopapi.entity.ProductInOrder;
import me.zhulin.shopapi.entity.User;
import me.zhulin.shopapi.repository.ProductCategoryRepository;
import me.zhulin.shopapi.service.CartService;
import me.zhulin.shopapi.service.CategoryService;
import me.zhulin.shopapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2147483641);

        List<ProductCategory> res1 = new ArrayList<>();
        res1.add(productCategory);

        Mockito.when(productCategoryRepository.findAllByOrderByCategoryType()).thenReturn(res1);

        List<ProductCategory> res2 = categoryService.findAll();

        assertEquals(res1.get(0).getCategoryId(), res2.get(0).getCategoryId());

    }

    @Test
    public void findByCategoryTypeTest() throws Exception {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2147483641);
        productCategory.setCategoryType(1);


        Mockito.when(productCategoryRepository.findByCategoryType(1)).thenReturn(productCategory);

        ProductCategory res = categoryService.findByCategoryType(1);

        assertEquals(productCategory.getCategoryId(), res.getCategoryId());

    }

    @Test
    public void saveNewCategoryTypeTest() throws Exception {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2147483641);
        productCategory.setCategoryType(1);


        Mockito.when(productCategoryRepository.save(productCategory)).thenReturn(productCategory);

        ProductCategory res = categoryService.save(productCategory);

        assertEquals(productCategory.getCategoryId(), res.getCategoryId());

    }
}
