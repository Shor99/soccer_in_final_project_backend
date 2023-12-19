package or.project.soccer_in_final_project.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import or.project.soccer_in_final_project.dto.*;
import or.project.soccer_in_final_project.service.CommentService;
import or.project.soccer_in_final_project.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@Tag(name = "Transfer controller", description = "All the transfers")
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
@SecurityRequirement(
        name = "Bearer Authentication"
)
public class TransferController {
    private final TransferService transferService;
    private final CommentService commentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransferResponseDto> addTransfer(@RequestBody @Valid TransferRequestDto transferRequestDto, UriComponentsBuilder uriBuilder){
        var saved = transferService.createTransfer(transferRequestDto);
        var uri = uriBuilder.path("/api/v1/transfers/{id}").buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<TransferResponseDto>> getAllTransfers(){
        return ResponseEntity.ok(transferService.getAllTransfers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDto> findTransferById(
            @Valid @NotNull @PathVariable long id){
        return ResponseEntity.ok(transferService.findTransferById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TransferResponseDto> updateTransferById(
            @Valid @NotNull @PathVariable long id,
            @Valid @RequestBody TransferRequestDto dto){

        return ResponseEntity.ok(transferService.updateTransferById(dto, id));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransferResponseDto> deleteTransferById(
            @Valid @NotNull @PathVariable long id){
        commentService.deleteAlCommentsByTransferId(id);
        return ResponseEntity.ok(transferService.deleteTransferById(id));
    }
    @GetMapping("/page")
    public ResponseEntity<TransferPageResponseDto> getPostsPage(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.ok(transferService.getAllTransfers(pageNo, pageSize, sortBy, sortDir));
    }
    @GetMapping("/searchByName")
    public ResponseEntity<TransferFindByNameResponseDto> getTransfersByPlayerFullName(
            @RequestParam(value = "playerFullName", required = false, defaultValue = "") String playerFullName
    ) {
        return ResponseEntity.ok(transferService.findTransfersByPlayerName(playerFullName));
    }
    @GetMapping("/searchByTeam")
    public ResponseEntity<TransferFindByTeamDto> getTransfersByTeamName(
            @RequestParam(value = "teamName", required = false, defaultValue = "") String teamName
    ) {
        return ResponseEntity.ok(transferService.findTransfersByTeamName(teamName));
    }
    @GetMapping("/searchByTransferFeeRange")
    public ResponseEntity<TransferFindByRangeFeeDto> getTransfersByTransferFeeRange(
            @RequestParam(value = "rangeFrom", required = false, defaultValue = "0") int rangeFrom,
            @RequestParam(value = "rangeTo", required = false, defaultValue = "0") int rangeTo
    ) {
        return ResponseEntity.ok(transferService.findTransfersByRangeOfFees(rangeFrom,rangeTo));
    }
}
