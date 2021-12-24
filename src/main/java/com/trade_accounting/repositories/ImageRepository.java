package com.trade_accounting.repositories;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.Position;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.models.dto.PositionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {
    @Query("select new com.trade_accounting.models.Image(" +
            "i.id, " +
            "i.imageUrl, " +
            "i.sortNumber) from Image as i")
    List<Image> getAll();


    @Query("select new com.trade_accounting.models.Image(" +
            "em.image.id, " +
            "em.image.sortNumber" +
            ") " +
            "from Employee em " +
            "where em.id = :id")
    Image getImageByEmployeeId(@Param("id") Long id);

    @Query("select p.images from Product p where p.id = :id")
    List<Image> getAllByProductId(@Param("id") Long id);

    @Query(value = "SELECT count(*) FROM products_images u WHERE u.images_id = :id",
            nativeQuery = true)
    int countProductImage(@Param("id") Long id);

    @Query("SELECT new com.trade_accounting.models.dto.ImageDto(" +
            "p.id, " +
            "p.sortNumber" +
            ") " +
            "FROM Image p " +
            "WHERE p.id = :id")
    ImageDto getById(@Param("id") Long id);

    Optional<Image> findByImageUrl(String imageUrl);

    Image getImageById(Long id);
}