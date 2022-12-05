package com.app.movie.service;

import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.Score;
import com.app.movie.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    ScoreRepository repository;

    public Iterable<Score> get() {
        Iterable<Score> response = repository.getAll();
        return response;
    }

    public ResponseDto create(Score request) {
        ResponseDto responseDto = new ResponseDto();
        if (request.getStarsNumber().intValue() <= 1 || request.getStarsNumber().intValue() >= 5) {
            responseDto.status = false;
            responseDto.message = "Su puntuacion debe ser un numero de 1 y 5";
        }else {
            repository.save(request);
            responseDto.status = true;
            responseDto.message = "Su puntuacion se h guardado correctamente";
            responseDto.id = request.getId();
        }
        return responseDto;
    }
    public Score update(Score score) {
        Score scoreToUpdate = new Score();
        if (repository.existsById(score.getId())) {
            scoreToUpdate = score;
            repository.save(scoreToUpdate);
        }
        return scoreToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}