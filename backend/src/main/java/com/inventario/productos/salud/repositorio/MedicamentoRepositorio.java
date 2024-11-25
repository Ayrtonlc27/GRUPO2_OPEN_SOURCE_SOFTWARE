package com.inventario.productos.salud.repositorio;

import com.inventario.productos.salud.entidades.Medicamentos;
import org.springframework.data.jpa.repository.JpaRepository;
//HU005
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MedicamentoRepositorio extends JpaRepository<Medicamentos, Long> {

    //HU005
    @Query("SELECT m FROM Medicamentos m")
    List<MedicamentosProjection> listarMedicamentos();

    //HU005
    @Query("SELECT m FROM Medicamentos m WHERE m.nombre like %:prefijo%")
    public List<MedicamentosProjection> obtenerListadoPorDescripcion(@Param("prefijo") String prefijo);

    //HU005
    @Query("SELECT m FROM Medicamentos m WHERE m.codigo like %:prefijo%")
    public List<MedicamentosProjection> obtenerListadoPorCodigo(@Param("prefijo") Long prefijo);
}
