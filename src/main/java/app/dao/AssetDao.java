package app.dao;

import app.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AssetDao extends JpaRepository<Asset, BigDecimal> {
}
