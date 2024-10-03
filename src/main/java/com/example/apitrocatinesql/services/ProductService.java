package com.example.apitrocatinesql.services;

import com.example.apitrocatinesql.exception.ForeignKeyConstraintException;
import com.example.apitrocatinesql.exception.NotFound;
import com.example.apitrocatinesql.exception.NotFoundCategory;
import com.example.apitrocatinesql.exception.NotFoundUser;
import com.example.apitrocatinesql.models.Category;
import com.example.apitrocatinesql.models.DTO.HighlightDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.EditProductRequestDTO;
import com.example.apitrocatinesql.models.DTO.requestDTO.SaveProductRequestDTO;
import com.example.apitrocatinesql.models.DTO.responseDTO.*;
import com.example.apitrocatinesql.models.Product;
import com.example.apitrocatinesql.models.Tag;
import com.example.apitrocatinesql.models.User;
import com.example.apitrocatinesql.repositories.CategoryRepository;
import com.example.apitrocatinesql.repositories.ProductRepository;
import com.example.apitrocatinesql.repositories.TagRepository;
import com.example.apitrocatinesql.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private TagRepository tagRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductService.class);
    public List<FindProductCardResponseDTO> findProductCard(){

        boolean highlight = true;

        List<Product> allProduct = productRepository.findAll().stream().limit(30).collect(Collectors.toList());

        if(allProduct.size() <= 0){
            throw new NotFound("Not found products");
        }

        List<FindProductCardResponseDTO> findProductCard = allProduct.stream()
                .map(product -> new FindProductCardResponseDTO(
                        product.getIdProduct(),
                        product.getValue(),
                        product.getCreatedAt(),
                        product.getName(),
                        product.getDescription(),
                        product.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                        product.getHighlights().stream().map(highlight1 -> new HighlightDTO(highlight1.getExpirantionAt(), highlight)).collect(Collectors.toList()),
                        product.getFlagTrade()
                ))
                .collect(Collectors.toList());

        return findProductCard;
    }

    public List<FindProductCardTradeResponseDTO> findProductCardTrade(){

        boolean highlight = true;

        List<Product> allProductTrade = productRepository.findProductByFlagTradeIsTrue().stream().limit(30).collect(Collectors.toList());

        if(allProductTrade.size() <= 0){
            throw new NotFound("Not found products");
        }

        List<FindProductCardTradeResponseDTO> findProductCard = allProductTrade.stream()
                .map(product -> new FindProductCardTradeResponseDTO(
                        product.getIdProduct(),
                        product.getValue(),
                        product.getCreatedAt(),
                        product.getName(),
                        product.getDescription(),
                        product.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                        product.getHighlights().stream().map(highlight1 -> new HighlightDTO(highlight1.getExpirantionAt(), highlight)).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        return findProductCard;
    }

    public List<FindProductCardBuyResponseDTO> findProductCardBuy(){

        boolean highlight = true;

        List<Product> allProductBuy = productRepository.findProductByFlagTradeIsFalse().stream().limit(30).collect(Collectors.toList());

        if(allProductBuy.size() <= 0){
            throw new NotFound("Not found products");
        }

        List<FindProductCardBuyResponseDTO> findProductCard = allProductBuy.stream()
                .map(product -> new FindProductCardBuyResponseDTO(
                        product.getIdProduct(),
                        product.getValue(),
                        product.getCreatedAt(),
                        product.getName(),
                        product.getDescription(),
                        product.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                        product.getHighlights().stream().map(highlight1 -> new HighlightDTO(highlight1.getExpirantionAt(), highlight)).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        return findProductCard;
    }

    public List<FindProductCardTagResponseDTO> findProductCardByTag(String nameTag){
        boolean highlight = true;

        Tag tag = tagRepository.findTagByNameIgnoreCase(nameTag);

        List<Product> allProductTag = productRepository.findProductByTags(tag).stream().limit(30).collect(Collectors.toList());

        if(allProductTag.size() <= 0){
            throw new NotFound("Not found products");
        }

        List<FindProductCardTagResponseDTO> findProductCardTag = allProductTag.stream()
                .map(product -> new FindProductCardTagResponseDTO(
                        product.getIdProduct(),
                        product.getValue(),
                        product.getCreatedAt(),
                        product.getName(),
                        product.getDescription(),
                        product.getTags().stream().map(tags -> tags.getName()).collect(Collectors.toList()),
                        product.getHighlights().stream().map(highlight1 -> new HighlightDTO(highlight1.getExpirantionAt(), highlight)).collect(Collectors.toList()),
                        product.getFlagTrade()                ))
                .collect(Collectors.toList());

        return findProductCardTag;
    }

    public List<FindProductCardCategoryResponseDTO> findProductCardByCategory(String nameCategoty){
        boolean highlight = true;

        Category category = categoryRepository.findCategoryByNameIgnoreCase(nameCategoty);

        List<Product> allProductCategory = productRepository.findProductByCategories(category).stream().limit(30).collect(Collectors.toList());

        if(allProductCategory.size() <= 0){
            throw new NotFound("Not found products");
        }

        List<FindProductCardCategoryResponseDTO> findProductCardCategory = allProductCategory.stream()
                .map(product -> new FindProductCardCategoryResponseDTO(
                        product.getIdProduct(),
                        product.getValue(),
                        product.getCreatedAt(),
                        product.getName(),
                        product.getDescription(),
                        product.getTags().stream().map(tags -> tags.getName()).collect(Collectors.toList()),
                        product.getHighlights().stream().map(highlight1 -> new HighlightDTO(highlight1.getExpirantionAt(), highlight)).collect(Collectors.toList()),
                        product.getFlagTrade()                ))
                .collect(Collectors.toList());

        return findProductCardCategory;
    }

    public List<FindProductCardNameResponseDTO> findProductCardByName(String name){
        boolean highlight = true;

        List<Product> allProductName = productRepository.findProductByNameIsContainingIgnoreCase(name).stream().limit(30).collect(Collectors.toList());

        if(allProductName.size() <= 0){
            throw new NotFound("Not found products");
        }

        List<FindProductCardNameResponseDTO> findProductCardName = allProductName.stream()
                .map(product -> new FindProductCardNameResponseDTO(
                        product.getIdProduct(),
                        product.getValue(),
                        product.getCreatedAt(),
                        product.getName(),
                        product.getDescription(),
                        product.getTags().stream().map(tags -> tags.getName()).collect(Collectors.toList()),
                        product.getHighlights().stream().map(highlight1 -> new HighlightDTO(highlight1.getExpirantionAt(), highlight)).collect(Collectors.toList()),
                        product.getFlagTrade()                ))
                .collect(Collectors.toList());

        return findProductCardName;
    }

    public List<FindProductCardListTagResponseDTO> findProductCardByListTag(List<String> tags){
        boolean highlight = true;

        List<Product> allProductListTag = productRepository.findProductByListTag(tags).stream().limit(30).collect(Collectors.toList());

        if(allProductListTag.size() <= 0){
            throw new NotFound("Not found products");
        }

        List<FindProductCardListTagResponseDTO> findProductCardListTag = allProductListTag.stream()
                .map(product -> new FindProductCardListTagResponseDTO(
                        product.getIdProduct(),
                        product.getValue(),
                        product.getCreatedAt(),
                        product.getName(),
                        product.getDescription(),
                        product.getTags().stream().map(getTag -> getTag.getName()).collect(Collectors.toList()),
                        product.getHighlights().stream().map(highlight1 -> new HighlightDTO(highlight1.getExpirantionAt(), highlight)).collect(Collectors.toList()),
                        product.getFlagTrade()                ))
                .collect(Collectors.toList());

        return findProductCardListTag;
    }

    public FindProductInformationResponseDTO findProductInformation(Long id){
        boolean highlight = true;

        Product product = productRepository.findProductByIdProduct(id);

        if(product == null){
            throw new NotFound("Not found product");
        }

        FindProductInformationResponseDTO newProduct= new FindProductInformationResponseDTO(
                        product.getIdProduct(),
                        product.getUser().getNickname(),
                        product.getValue(),
                        product.getCreatedAt(),
                        product.getName(),
                        product.getDescription(),
                        product.getTags().stream().map(getTag -> getTag.getName()).collect(Collectors.toList()),
                        product.getHighlights().stream().map(highlight1 -> new HighlightDTO(highlight1.getExpirantionAt(), highlight)).collect(Collectors.toList()),
                        product.getFlagTrade()                );
        return newProduct;
    }

    public List<FindProductByUserResponseDTO> findPorductByUser(String email){
        boolean highlight = true;

        User user = userRepository.findUserByEmail(email);

        if(user == null){
            throw new NotFoundUser("Not found user");
        }

        List<Product> allProductByUser = productRepository.findProductByUser(user);

        if(allProductByUser == null){
            throw new NotFound("Not found product");
        }

        List<FindProductByUserResponseDTO> findProductByUser = allProductByUser.stream()
                .map(product -> new FindProductByUserResponseDTO(
                        product.getIdProduct(),
                        product.getValue(),
                        product.getCreatedAt(),
                        product.getName(),
                        product.getDescription(),
                        product.getTags().stream().map(getTag -> getTag.getName()).collect(Collectors.toList()),
                        product.getHighlights().stream().map(highlight1 -> new HighlightDTO(highlight1.getExpirantionAt(), highlight)).collect(Collectors.toList()),
                        product.getFlagTrade()                ))
                .collect(Collectors.toList());

        return findProductByUser;
    }

    @Transactional
    public DeleteProductResponseDTO deleteProduct(Long id)  {
        boolean delete = false;
        Product product = productRepository.findProductByIdProduct(id);

        if (product != null) {
            try {
                productRepository.deleteById(id);
                delete = true;
            } catch (DataIntegrityViolationException e) {
                throw new ForeignKeyConstraintException("This product can´t delete, because have foreign key in other table");
            }
        }
        return new DeleteProductResponseDTO(delete);
    }

    public SaveProductResponseDTO saveProduct(SaveProductRequestDTO productRequest) {
        Set<Tag> tags = productRequest.tags()
                .stream()
                .map(tag -> {
                    Tag tagFind = tagRepository.findTagByNameIgnoreCase(tag.name());
                    if(tagFind == null){
                        Tag newTag = new Tag();
                        newTag.setName(tag.name());
                        newTag.setType(tag.type());
                        tagFind = tagRepository.save(newTag);
                    }
                    return tagFind;
                })
                .collect(Collectors.toSet());
        Set<Category> categories = productRequest.categories()
                .stream()
                .map(category -> {
                    Category categoryFind = categoryRepository.findCategoryByNameIgnoreCase(category);
                    if (categoryFind == null) {
                        throw new NotFoundCategory("Not found category");
                    }
                    return categoryFind;
                    }).collect(Collectors.toSet());

        User user = userRepository.findUserByEmail(productRequest.emailUser());
        if (user == null) {
            throw new NotFoundUser("User not found for email: " + productRequest.emailUser());
        }

        Product productFinally = new Product();
        productFinally.setUser(user);

        productFinally.setName(productRequest.name());
        productFinally.setDescription(productRequest.description());
        productFinally.setValue(productRequest.value());
        productFinally.setStock(productRequest.stock());
        productFinally.setCreatedAt(LocalDate.now());
        productFinally.setUpdateAt(LocalDate.now());
        productFinally.setFlagTrade(productRequest.flagTrade());
        productFinally.setCategories(categories);
        productFinally.setTags(tags);

        productRepository.save(productFinally);

        return new SaveProductResponseDTO(true);
    }

    public EditProductResponseDTO editProduct(EditProductRequestDTO productRequest) {

        Product savedProduct = productRepository.findProductByIdProduct(productRequest.id());

        Set<Tag> tags = productRequest.tags()
                .stream()
                .map(tag -> {
                    Tag tagFind = tagRepository.findTagByNameIgnoreCase(tag.name());
                    if(tagFind == null){
                        Tag newTag = new Tag();
                        newTag.setName(tag.name());
                        newTag.setType(tag.type());
                        tagFind = tagRepository.save(newTag);
                    }
                    return tagFind;
                })
                .collect(Collectors.toSet());
        Set<Category> categories = productRequest.categories()
                .stream()
                .map(categoryName -> {
                    Category categoryFind = categoryRepository.findCategoryByNameIgnoreCase(categoryName);
                    if (categoryFind == null) {
                        throw new NotFoundCategory("Not found category");
                    }
                    return categoryFind;
                })
                .collect(Collectors.toSet());


        savedProduct.setName(productRequest.name());
        savedProduct.setDescription(productRequest.description());
        savedProduct.setValue(productRequest.value());
        savedProduct.setStock(productRequest.stock());
        savedProduct.setUpdateAt(LocalDate.now());
        savedProduct.setFlagTrade(productRequest.flagTrade());
        savedProduct.setCategories(categories);
        savedProduct.setTags(tags);

        Product newProduct = productRepository.save(savedProduct);

        return new EditProductResponseDTO(
                newProduct.getUser().getEmail(),
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getValue(),
                newProduct.getStock(),
                newProduct.getFlagTrade(),
                newProduct.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                newProduct.getCategories().stream().map(category -> category.getName()).collect(Collectors.toList())
        );
    }







}
