/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica1_JosueAlfaroCamacho.service;

import Practica1_JosueAlfaroCamacho.domain.Arbol;
import Practica1_JosueAlfaroCamacho.repository.ArbolRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArbolService {

    private final ArbolRepository arbolRepository;
   // Inyección por constructor
    public ArbolService(ArbolRepository arbolRepository) {
        this.arbolRepository = arbolRepository;
    }

    // 🔹 LISTAR
    @Transactional(readOnly = true)
    public List<Arbol> getArboles() {
        return arbolRepository.findAll();
    }

    // 🔹 OBTENER POR ID
    @Transactional(readOnly = true)
    public Optional<Arbol> getArbol(Integer idArbol) {
        return arbolRepository.findById(idArbol);
    }

 @Autowired
    private FirebaseStoregeService firebaseStoregeService;

    @Transactional
    public void save(Arbol arbol, MultipartFile imagenFile) {
        arbol = arbolRepository.save(arbol);
        if (!imagenFile.isEmpty()) { //Si no está vacío... pasaron una imagen...            
            try {
                String rutaImagen = firebaseStoregeService.uploadImage(imagenFile, "arbol",arbol.getIdArbol());
                arbol.setRutaImagen(rutaImagen);
                arbolRepository.save(arbol);
            } catch (IOException e) {
              

            }
        }
    }

    // 🔹 ELIMINAR
    @Transactional
    public void delete(Integer idArbol) {

        if (!arbolRepository.existsById(idArbol)) {
            throw new IllegalArgumentException(
                    "El árbol con ID " + idArbol + " no existe.");
        }

        try {
            arbolRepository.deleteById(idArbol);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException(
                    "No se puede eliminar el árbol. Tiene datos asociados.", e);
        }
    }
}
