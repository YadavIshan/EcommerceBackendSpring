package com.ishan.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@org.springframework.test.context.TestPropertySource(properties = {
		"FAKESTORE_API_URL=https://fakestoreapi.com",
		"BASE_URL=https://fakestoreapi.com",
		"fakeStore.category.url=https://fakestoreapi.com/api/products/category"
})
class EcommerceBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
