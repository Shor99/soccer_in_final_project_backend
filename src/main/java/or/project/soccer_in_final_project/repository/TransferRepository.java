package or.project.soccer_in_final_project.repository;

import or.project.soccer_in_final_project.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TransferRepository extends JpaRepository<Transfer,Long> {
    List<Transfer> findByPlayerFullNameContainsIgnoreCase(String playerFullName);
    Optional<Transfer> findByFromTeam(String fromTeam);
    Optional<Transfer> findByToTeam(String toTeam);

    List<Transfer> findTransferByTransferFeeInMillionsBetween(int rangeFrom, int rangeTo);
}
