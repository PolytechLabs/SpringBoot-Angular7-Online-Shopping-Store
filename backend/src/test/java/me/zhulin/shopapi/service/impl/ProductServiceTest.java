package me.zhulin.shopapi.service.impl;

import me.zhulin.shopapi.entity.ProductInfo;
import me.zhulin.shopapi.enums.ResultEnum;
import me.zhulin.shopapi.exception.MyException;
import me.zhulin.shopapi.repository.ProductInfoRepository;
import me.zhulin.shopapi.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest
{
    @Mock
    private ProductInfoRepository productInfoRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deleteSuccessfulTest() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(1);
        productInfo.setProductId("12ds2");


        Mockito.when(productInfoRepository.findByProductId("12ds2")).thenReturn(productInfo);

        productService.delete("12ds2");

        verify(productInfoRepository, times(1)).delete(productInfo);

    }

    @Test(expected = MyException.class)
    public void deleteUnsuccessfulTest() throws Exception {
        Mockito
                .when(productInfoRepository.findByProductId("12ds2"))
                .thenThrow(MyException.class);
        productService.delete("12ds2");    }
}
