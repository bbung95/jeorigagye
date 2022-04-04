package com.jeorigagye.service;

import com.jeorigagye.domain.Category;
import com.jeorigagye.domain.Expenditure;
import com.jeorigagye.domain.Member;
import com.jeorigagye.repository.ExpenditureRepository;
import com.jeorigagye.repository.MemberRepsitory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class ExpenditureServiceTest {

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Autowired
    private MemberRepsitory memberRepsitory;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setCategory(){
        Category category = Category.builder()
                .name("식사")
                .image("image1")
                .build();

        em.persist(category);
        em.flush();
    }

    @Test
    @DisplayName("정기지출 등록_TEST")
    public void expenditureRegistTest() throws Exception {
        //given
        Category category = em.find(Category.class, 1L);
        Member member = getMember("bbung95");

        Expenditure expenditure = getExpenditure(member , category);

        //when
        expenditureRepository.save(expenditure);

        //then
        Expenditure findExpenditure = expenditureRepository.findById(expenditure.getId()).get();

        assertThat(expenditure.getPrice()).isEqualTo(findExpenditure.getPrice());
        assertThat(expenditure.getName()).isEqualTo(findExpenditure.getName());
        assertThat(expenditure.getCategory()).isEqualTo(findExpenditure.getCategory());
    }

    @Test
    @DisplayName("정기지출 삭제_TEST")
    public void expenditureRemoveTest() throws Exception {
        //given
        Category category = em.find(Category.class, 1L);
        Member member = getMember("bbung95");

        Expenditure expenditure = getExpenditure(member , category);

        //when
        expenditureRepository.save(expenditure);
        em.flush();

        Expenditure findExpenditure = expenditureRepository.findById(expenditure.getId()).get();
        expenditureRepository.delete(findExpenditure);

        List<Expenditure> checkExpenditure = expenditureRepository.findAll();

        //then

        assertThat(checkExpenditure.size()).isEqualTo(0);
    }

    public Expenditure getExpenditure(Member member, Category category){
        Expenditure expenditure = Expenditure.builder()
                .price(69000)
                .name("휴대폰 통신비")
                .member(member)
                .category(category)
                .build();
        return expenditure;
    }

    public Member getMember(String membername){

        Member member = Member.builder()
                .membername(membername)
                .password("1234")
                .name("뻥뻥이")
                .build();

        return member;
    }
}