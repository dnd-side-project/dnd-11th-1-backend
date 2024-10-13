package com.dnd.accompany.domain.qna100.service;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnd.accompany.domain.qna100.api.dto.CreateAndUpdateQnaRequest;
import com.dnd.accompany.domain.qna100.entity.Qna100;
import com.dnd.accompany.domain.qna100.infrastructure.QnaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {

	private final QnaRepository qnaRepository;

	@Transactional
	public void saveAndUpdate(Long userId, CreateAndUpdateQnaRequest request) {
		Map<Boolean, List<Qna100>> qnaMap = request.toMap(userId, request.qnas());

		List<Qna100> newQnas = qnaMap.get(true);
		List<Qna100> existingQnas = qnaMap.get(false);

		Map<Long, Qna100> existingQnaMap = existingQnas.stream()
			.collect(toMap(Qna100::getId, Function.identity()));

		List<Qna100> qnas = qnaRepository.findAllById(existingQnaMap.keySet());

		qnas.forEach(qna -> {
			Long qnaId = qna.getId();
			qna.setQuestion(existingQnaMap.get(qnaId).getQuestion());
			qna.setAnswer(existingQnaMap.get(qnaId).getAnswer());
		});

		qnaRepository.saveAll(newQnas);
	}
}
