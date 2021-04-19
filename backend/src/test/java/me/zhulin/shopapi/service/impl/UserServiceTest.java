package me.zhulin.shopapi.service.impl;

import me.zhulin.shopapi.entity.Cart;
import me.zhulin.shopapi.entity.User;
import me.zhulin.shopapi.repository.CartRepository;
import me.zhulin.shopapi.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest
{
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private UserServiceImpl userService;



    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void changeDataTest() throws Exception {
        User user = new User();
        user.setEmail("gggg@ggdjd");
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

        User user1 = new User();
        user1.setEmail("dcbfhsjbmc@ggdjd");
        user1.setActive(true);
        user1.setId(11L);
        user1.setPassword("sdafds");
        user1.setName("aaaaa");
        user1.setPhone("why");
        user1.setAddress("help");

        Cart cart1 = new Cart();
        cart1.setCartId(1222L);
        cart1.setUser(user1);

        user1.setCart(cart1);

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user1);

        User res = userService.update(user);

        assertEquals(user.getId(), res.getId());
        assertEquals("dcbfhsjbmc@ggdjd", res.getEmail());
        assertNotEquals(user.getEmail(), user1.getEmail());

    }
}
