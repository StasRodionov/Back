package com.trade_accounting.repositories;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.dto.ImageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query("select new com.trade_accounting.models.dto.ImageDto(" +
            "i.id, " +
            "i.imageUrl, " +
            "i.sortNumber) from Image as i")
    List<ImageDto> getAll();

    @Query("select new com.trade_accounting.models.dto.ImageDto(" +
            "i.id, " +
            "i.imageUrl, " +
            "i.sortNumber) from Image as i where i.id = :id")
    ImageDto getById(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ImageDto(" +
            "em.image.id, " +
            "em.image.imageUrl, " +
            "em.image.sortNumber" +
            ") " +
            "from Employee em " +
            "where em.id = :id")
    ImageDto getImageByEmployeeId(@Param("id") Long id);

    @Query("select new com.trade_accounting.models.dto.ImageDto(image.id, image.imageUrl, image.sortNumber) " +
            "from Product p inner join p.images as image where p.id = :id")
    List<ImageDto> getAllByProductId(@Param("id") Long id);

    Optional<Image> findByImageUrl(String imageUrl);
}