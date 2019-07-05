package com.ahmettutal.repository;

import com.ahmettutal.model.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends BaseRepository<Picture, Long> {

    List<Picture> findAllByProductId(Long productId);

    List<Picture> findAllByCategoryId(Long categoryId);

    Picture findByCategoryId(Long categoryId);

}
