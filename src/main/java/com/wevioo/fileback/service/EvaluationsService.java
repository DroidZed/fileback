package com.wevioo.fileback.service;

import com.wevioo.fileback.interfaces.EvaluationsManager;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Evaluation;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.EvaluationRepository;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EvaluationsService implements EvaluationsManager {

    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseMessage> publishEvaluation(Evaluation eval, Long jobberId) {

        Optional<User> opt = this.userRepository.findById(jobberId);

        if (opt.isEmpty())
            return ResponseEntity.status(404).body(new ResponseMessage("JOBBER NOT FOUND !"));

        User jobber = opt.get();

        eval.setEvaluated(jobber);

        this.evaluationRepository.save(eval);

        return ResponseEntity.ok(new ResponseMessage("Evaluation published !"));
    }

    @Override
    public ResponseEntity<?> collectEvaluationsOfJobber(Long jobberId) {

        return ResponseEntity.ok(this.evaluationRepository.findAllByEvaluatedId(jobberId));

    }
}
