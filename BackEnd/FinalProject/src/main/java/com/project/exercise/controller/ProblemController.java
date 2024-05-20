package com.project.exercise.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.exercise.dto.Problem;
import com.project.exercise.dto.SearchCondition;
import com.project.exercise.dto.User;
import com.project.exercise.dto.UserData;
import com.project.exercise.service.ProblemService;
import com.project.exercise.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/ansmoon")
@Tag(name = "Problem Main Control", description = "문제 랜덤 추출 [Author : ANSMOON]")
public class ProblemController {
	private final ProblemService problemService;
	private final UserService userService;

	private int correctCount = 0;

	List<Problem> list;

	@Autowired
	public ProblemController(ProblemService problemService, UserService userService) {
		this.problemService = problemService;
		this.userService = userService;
	}

	// 문제 랜덤 추출
	@GetMapping("/problem/list")
	@Operation(summary = "문제 추출", description = "카테고리별로 문제 랜덤 추출")
	public ResponseEntity<?> listUp() {
		try {
			list = problemService.getListAll();

			if (list == null || list.isEmpty()) {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Problem>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("문제 추출 중에 오류가 발생했습니다: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 문제의 정답 확인
	@PostMapping("/problem/check")
	@Operation(summary = "정답 확인", description = "입력받은 정답과 실제 정답 일치 여부 확인")
	public ResponseEntity<Boolean> checkAnswer(@RequestParam int index, @RequestParam String answer) {
		try {
			boolean isCorrect = answer.equals(list.get(index).getProblemAnswer());

			if (isCorrect) {
				correctCount++;
			}

			return ResponseEntity.ok(isCorrect);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 정답 갯수 반환
	@GetMapping("/problem/correctCount")
	@Operation(summary = "정답 갯수 반환", description = "사용자가 맞춘 정답의 총 갯수 반환")
	public ResponseEntity<Integer> getCorrectCount() {
		return ResponseEntity.ok(correctCount);
	}

	// 최종 점수 반환
	@GetMapping("/problem/finalScore")
	@Operation(summary = "최종 점수 반환", description = "사용자의 최종 점수 반환")
	public ResponseEntity<Integer> getFinalScore() {
		// 사용자의 최종 점수를 계산하고 반환하는 로직을 구현합니다.
		int finalScore = calculateFinalScore();
		return ResponseEntity.ok(finalScore);
	}

	// 사용자의 최종 점수를 계산하는 메서드
	private int calculateFinalScore() {
		// 여기에서 사용자의 최종 점수를 계산하는 로직을 구현합니다.
		// 예를 들어, 맞춘 문제 수와 틀린 문제 수 등을 고려하여 최종 점수를 계산할 수 있습니다.
		// 아래는 임시적인 예시입니다.
		int totalScore = correctCount * 10; // 맞춘 문제 수에 따라 점수를 계산합니다.
		return totalScore;
	}

	// 최종 결과 저장
	@PostMapping("/problem/save")
	@Operation(summary = "결과 저장", description = "현재까지의 결과를 저장")
	public ResponseEntity<UserData> save(HttpSession session, @RequestParam("score") int score) {
		try {
			System.out.println(session.getAttribute("nickName"));
			User user = userService.getUser(session.getAttribute("nickName").toString());
			System.out.println("session : " + user);
			if (user == null) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			Date now = new Date();
			UserData savedata = new UserData(user.getUserId(), user.getNickName(), user.getUserName(),
					score, now);
			System.out.println(savedata);
			problemService.saveUserData(savedata);
			return new ResponseEntity<>(savedata, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/problem/search")
	public ResponseEntity<List<UserData>> searchList(@ModelAttribute SearchCondition searchCondition){
		List<UserData> list = problemService.searchList(searchCondition);
		return new ResponseEntity<List<UserData>>(list, HttpStatus.OK);
	}
}