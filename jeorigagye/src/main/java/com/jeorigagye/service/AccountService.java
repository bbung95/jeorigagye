package com.jeorigagye.service;

import com.jeorigagye.domain.Account;
import com.jeorigagye.domain.Category;
import com.jeorigagye.domain.Member;
import com.jeorigagye.dto.Search;
import com.jeorigagye.dto.account.AccountDto;
import com.jeorigagye.dto.account.AccountForm;
import com.jeorigagye.dto.account.AccountSumPriceDto;
import com.jeorigagye.enums.AccountType;
import com.jeorigagye.repository.AccountRepository;
import com.jeorigagye.repository.CategoryRepository;
import com.jeorigagye.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final MemberRepsitory memberRepsitory;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<Long> addAccount(AccountForm accountForm){

        Member findMember = memberRepsitory.findById(accountForm.getMemberId()).get();
        Category findCategory = categoryRepository.findById(accountForm.getCatId()).get();

        Account account = Account.builder()
                .price(accountForm.getPrice())
                .name(accountForm.getName())
                .type(accountForm.getType())
                .member(findMember)
                .category(findCategory)
                .build();

        accountRepository.save(account);

        return new ResponseEntity<>(account.getId(), HttpStatus.OK);
    }

    public ResponseEntity<List<AccountDto>> findAll(Search search){

        PageRequest pageRequest = PageRequest.of(search.getCulPage(), 10);

        Page<Account> findAccounts = accountRepository.findAccountBySaerch(AccountType.valueOf(search.getType()), pageRequest);

        List<AccountDto> accountList = findAccounts.getContent()
                .stream()
                .map(Account::toAccountDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    public ResponseEntity<AccountSumPriceDto> accountSumPrice(Long member_id){

        int incomeSumPrice = accountRepository.findByMemberIdAndTypeWithAccountSumPrice(member_id, AccountType.INCOME);
        int expenditureSumPrice = accountRepository.findByMemberIdAndTypeWithAccountSumPrice(member_id, AccountType.EXPENDITURE);

        AccountSumPriceDto sumPriceDto = AccountSumPriceDto.builder()
                .incomeSumPrice(incomeSumPrice)
                .expenditureSumPrice(expenditureSumPrice)
                .build();

        return new ResponseEntity<>(sumPriceDto, HttpStatus.OK);
    }

    public ResponseEntity deleteAccount(Long accountId) {

        Account findAccount = accountRepository.findById(accountId).get();
        accountRepository.delete(findAccount);

        return new ResponseEntity(HttpStatus.OK);
    }
}
