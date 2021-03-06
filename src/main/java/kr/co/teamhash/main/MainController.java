package kr.co.teamhash.main;

import kr.co.teamhash.account.CurrentUser;
import kr.co.teamhash.domain.entity.Account;
import kr.co.teamhash.domain.entity.ProjectMember;
import kr.co.teamhash.domain.repository.AccountRepository;
import kr.co.teamhash.project.form.ProjectBuildForm;
import kr.co.teamhash.project.ProjectService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProjectService projectService;
    private final AccountRepository accountRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    // 유저 메인 페이지에서 프로젝트의 리스트를 받기 위해
    // projectService에서 받은 뒤 템플릿으로 전송
    @GetMapping("/main")
    public String main(@CurrentUser Account account, Model model){
        if(account != null){
            Account byNickname = accountRepository.findByNickname(account.getNickname());
            List<ProjectMember> projectList = byNickname.getProjects();
            model.addAttribute("projectBuildForm", new ProjectBuildForm());
            model.addAttribute("projectList", projectList);
            model.addAttribute(account);

        }
        return "main";
    }

}
