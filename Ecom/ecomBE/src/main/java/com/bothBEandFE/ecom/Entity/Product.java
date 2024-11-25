package com.bothBEandFE.ecom.Entity;

import javax.persistence.*;

import com.bothBEandFE.ecom.Dto.ProductDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Long price;

	@Lob
	private String description;

	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] img;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Category category;

	public ProductDto getDto() {
		ProductDto productDto = new ProductDto();
		productDto.setId(id);
		productDto.setName(name);
		productDto.setPrice(price);
		productDto.setDescription(description);
		productDto.setByteImg(img);
		productDto.setCategoryId(category.getId());
		productDto.setCategoryName(category.getName());
		return productDto;
	}
}