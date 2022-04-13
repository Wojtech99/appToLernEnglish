package com.example.englishlerningapp.flashcard;

import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resource-flashcard")
public class FlashcardResource {

    private final FlashcardService flashcardService;

    public FlashcardResource(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping("/{id}")
    ResponseEntity<FlashcardDto> getFlashcardById(@Valid @PathVariable Long id) {
        return flashcardService.takeFlashcard(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all-categories")
    ResponseEntity<List<FlashcardDto>> getAllFlashcards() {
         return flashcardService.takeAllFlashcards()
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save-flashcard")
    ResponseEntity<FlashcardDto> saveFlashcard(@Valid @RequestBody FlashcardDto flashcardDto) {
        FlashcardDto savedFlashcard = flashcardService.saveFlashcard(flashcardDto);
        URI savedFlashcardUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedFlashcard.getId())
                .toUri();

        return ResponseEntity.created(savedFlashcardUri).body(savedFlashcard);
    }

    //Funkcja jest źle zrobiona w warstwie serwisu, id jest przyjmowane
    //aby adres htpp wiedział do czego się odwołać (Patrz category service, tam jest dobrze)
    @PatchMapping("/save")
    ResponseEntity<?> updateFlashcard(@Valid @RequestBody FlashcardDto flashcardDto) {
        return flashcardService.updateFlashcard(flashcardDto)
                .map(flashcard -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteFlashcard(@Valid @PathVariable Long id) {
        flashcardService.deleteFlashcard(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleMethodNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
