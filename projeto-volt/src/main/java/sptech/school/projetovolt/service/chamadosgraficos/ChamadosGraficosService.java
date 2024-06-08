package sptech.school.projetovolt.service.chamadosgraficos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.vwchamadosgraficos.VwChamadosGraficos;
import sptech.school.projetovolt.entity.vwchamadosgraficos.repository.VwChamadosGraficosRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChamadosGraficosService {
    private final VwChamadosGraficosRepository repository;

    public List<VwChamadosGraficos> capturarChamadosCanceladosConcluidos(){
        return repository.chamadosGraficoColuna();
    }
}
