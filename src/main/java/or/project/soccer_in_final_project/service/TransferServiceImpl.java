package or.project.soccer_in_final_project.service;

import lombok.RequiredArgsConstructor;
import or.project.soccer_in_final_project.dto.*;
import or.project.soccer_in_final_project.entities.Comment;
import or.project.soccer_in_final_project.entities.Transfer;
import or.project.soccer_in_final_project.error.ResourceNotFoundException;
import or.project.soccer_in_final_project.repository.CommentRepository;
import or.project.soccer_in_final_project.repository.TransferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {
    private final ModelMapper modelMapper;
    private final TransferRepository transferRepository;
    private final CommentRepository commentRepository;
    @Override
    public TransferResponseDto createTransfer(TransferRequestDto transferRequestDto) {
        //convert requestDTO To Entity
        var entity = modelMapper.map(transferRequestDto, Transfer.class);
        //save the entity
        var saved = transferRepository.save(entity);
        //convert saved entity to ResponseDto
        return modelMapper.map(saved, TransferResponseDto.class);
    }
    private Transfer getTransferEntity(long id){
        return transferRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Transfer", id)
        );
    }
    @Override
    public TransferResponseDto updateTransferById(TransferRequestDto dto, long id) {
        Transfer transferFromDb = getTransferEntity(id);

        transferFromDb.setPlayerLogoUrl(dto.getPlayerLogoUrl());
        transferFromDb.setPlayerFullName(dto.getPlayerFullName());
        transferFromDb.setTransferFeeInMillions(dto.getTransferFeeInMillions());
        transferFromDb.setFromTeam(dto.getFromTeam());
        transferFromDb.setFromTeamLogoUrl(dto.getFromTeamLogoUrl());
        transferFromDb.setToTeam(dto.getToTeam());
        transferFromDb.setToTeamLogoUrl(dto.getToTeamLogoUrl());
        transferFromDb.setNumberOfLikes(dto.getNumberOfLikes());

        var saved = transferRepository.save(transferFromDb);


        return modelMapper.map(saved, TransferResponseDto.class);
    }

    @Override
    public TransferResponseDto findTransferById(long id) {
        return modelMapper.map(transferRepository.findById(id), TransferResponseDto.class);
    }
    @Override
    public TransferFindByNameResponseDto findTransfersByPlayerName(String playerFullName) {
        return TransferFindByNameResponseDto.builder()
                .results(transferRepository.findByPlayerFullNameContainsIgnoreCase(playerFullName).stream().map(transfer -> modelMapper.map(transfer, TransfersWithCommentsDto.class)).toList())
                .playerFullName(playerFullName)
                .build();
    }

    @Override
    public TransferFindByTeamDto findTransfersByTeamName(String teamName) {
        List<TransfersWithCommentsDto> fromTeam = transferRepository.findByFromTeam(teamName).stream().map(transfer -> modelMapper.map(transfer, TransfersWithCommentsDto.class)).toList();
        List<TransfersWithCommentsDto> toTeam = transferRepository.findByToTeam(teamName).stream().map(transfer -> modelMapper.map(transfer, TransfersWithCommentsDto.class)).toList();
        List<TransfersWithCommentsDto> all = Stream.concat(toTeam.stream(),fromTeam.stream()).toList();
        return TransferFindByTeamDto.builder()
                .results(all)
                .teamName(teamName)
                .build();
    }

    @Override
    public TransferFindByRangeFeeDto findTransfersByRangeOfFees(int rangeFrom, int rangeTo) {
        return TransferFindByRangeFeeDto.builder()
                .results(transferRepository.findTransferByTransferFeeInMillionsBetween(rangeFrom,rangeTo).stream().map(transfer -> modelMapper.map(transfer,TransfersWithCommentsDto.class)).toList())
                .rangeFrom(rangeFrom)
                .rangeTo(rangeTo)
                .build();
    }

    @Override
    public TransferResponseDto deleteTransferById(long id) {
        Transfer transfer = getTransferEntity(id);
        transferRepository.deleteById(id);
        return modelMapper.map(transfer, TransferResponseDto.class);
    }

    @Override
    public List<TransferResponseDto> getAllTransfers() {
        return transferRepository
                .findAll()
                .stream()
                .map(p -> modelMapper.map(p, TransferResponseDto.class))
                .toList();
    }

    @Override
    public TransferPageResponseDto getAllTransfers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortBy);

        Page<Transfer> page = transferRepository.findAll(pageable);

        return TransferPageResponseDto.builder()
                .results(page.stream().map(transfer -> modelMapper.map(transfer, TransfersWithCommentsDto.class)).toList())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .pageNo(page.getNumber())
                .totalPosts(page.getTotalElements())
                .isLast(page.isLast())
                .isFirst(page.isFirst())
                .build();
    }
}
