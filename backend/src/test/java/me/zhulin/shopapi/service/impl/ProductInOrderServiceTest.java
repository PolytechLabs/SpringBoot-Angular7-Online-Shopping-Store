package me.zhulin.shopapi.service.impl;

import me.zhulin.shopapi.entity.Cart;
import me.zhulin.shopapi.entity.ProductCategory;
import me.zhulin.shopapi.entity.ProductInOrder;
import me.zhulin.shopapi.entity.User;
import me.zhulin.shopapi.repository.ProductCategoryRepository;
import me.zhulin.shopapi.repository.ProductInOrderRepository;
import me.zhulin.shopapi.service.CategoryService;
import me.zhulin.shopapi.service.ProductInOrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ProductInOrderServiceTest
{
    @Mock
    private ProductInOrderRepository productInOrderRepository;

    @InjectMocks
    private ProductInOrderServiceImpl productInOrderService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateTest() throws Exception {
        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setId(2147483641L);

        User user = new User();
        user.setEmail("gggg@ggdjd");
        user.setActive(true);
        user.setId(11L);

        Cart cart = new Cart();
        cart.setCartId(122L);
        cart.setUser(user);

        user.setCart(cart);

        Optional<ProductInOrder> productInOrder1 = Optional.ofNullable(productInOrder);


        Mockito.when(productInOrderRepository.findById(2147483641L)).thenReturn(productInOrder1);

        productInOrderService.update("2147483641", 2, user);

        Optional<ProductInOrder> res = productInOrderRepository.findById(2147483641L);

        assertEquals(productInOrder.getId(), res.get().getId());

    }

}
