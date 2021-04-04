package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.Evaluation;
import org.springframework.http.ResponseEntity;

public interface EvaluationsManager {

    ResponseEntity<ResponseMessage> publishEvaluation(Evaluation eval, Long jobberId);

    ResponseEntity<?> collectEvaluationsOfJobber(Long jobberId);
}
