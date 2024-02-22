package com.raswanth.cartmicroservice.controller;

import com.raswanth.cartmicroservice.dto.AddCartItemBodyDto;
import com.raswanth.cartmicroservice.dto.DeletecartItem;
import com.raswanth.cartmicroservice.entity.Cart;
import com.raswanth.cartmicroservice.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/cart")
@Validated
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AddCartItemBodyDto addCartItemBodyDto, Principal principal) {
        cartService.addToCart(addCartItemBodyDto, principal);
        return ResponseEntity.ok("Added product to cart");
    }

    @GetMapping("/view-cart")
    public Cart viewCart(Principal signedInUser) {
        return cartService.viewCart(signedInUser);
    }

    @DeleteMapping("/delete-item")
    public ResponseEntity<String> deleteCartItem(@Valid @RequestBody DeletecartItem deleteCartItemDTO) {
        cartService.deleteCartItem(deleteCartItemDTO);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
