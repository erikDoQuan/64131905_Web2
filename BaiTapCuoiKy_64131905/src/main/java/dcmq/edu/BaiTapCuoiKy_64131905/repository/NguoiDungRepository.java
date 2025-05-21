package dcmq.edu.BaiTapCuoiKy_64131905.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dcmq.edu.BaiTapCuoiKy_64131905.model.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {

    Optional<NguoiDung> findByEmail(String email);


    Page<NguoiDung> findByRole(int role, Pageable pageable);


    Page<NguoiDung> findByRoleAndHoTenContainingIgnoreCase(int role, String hoTen, Pageable pageable);

    @Query("SELECT nd FROM NguoiDung nd WHERE nd.role = 0 OR nd.role = 1")
    Page<NguoiDung> findAllRole0Or1(Pageable pageable);

 
    @Query("SELECT nd FROM NguoiDung nd WHERE (nd.role = 0 OR nd.role = 1) AND LOWER(nd.hoTen) LIKE LOWER(CONCAT('%', :hoTen, '%'))")
    Page<NguoiDung> findAllRole0Or1AndHoTenContainingIgnoreCase(@Param("hoTen") String hoTen, Pageable pageable);

}
