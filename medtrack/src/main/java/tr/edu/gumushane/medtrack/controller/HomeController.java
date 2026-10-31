package tr.edu.gumushane.medtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tr.edu.gumushane.medtrack.repository.MemberRepository;

@Controller
public class HomeController {

	@Autowired
	private MemberRepository memberRepository;

	@GetMapping("/")
	public String home(Authentication authentication) {
		return memberRepository.findByEmail(authentication.getName())
				.map(member -> "redirect:/dashboard/" + member.getId())
				.orElse("redirect:/dashboard/1");
	}
}
