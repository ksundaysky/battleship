package wkbp.battleships.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wkbp.battleships.model.Summary;

/**
 * @author Wiktor Rup
 */
public interface SummaryRepository extends JpaRepository<Summary, Long> {
}
