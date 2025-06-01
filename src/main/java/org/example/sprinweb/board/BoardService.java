package org.example.sprinweb.board;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.sprinweb.user.User;
import org.example.sprinweb.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repo;
    private final UserRepository  userRepo;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public void save(String title, String content, MultipartFile file, Authentication auth) throws IOException {
        Board b = new Board();
        b.setTitle(title);
        b.setContent(content);

        String username = auth.getName();
        b.setWriterName(username);

        if (!file.isEmpty()) {
            Files.createDirectories(Paths.get(uploadDir));
            String ext = Path.of(file.getOriginalFilename()).getFileName().toString();
            String stored = UUID.randomUUID() + "-" + ext;
            Path target = Paths.get(uploadDir).resolve(stored);
            file.transferTo(target);

            b.setStoredName(stored);
            b.setOriginalName(file.getOriginalFilename());
        }
        repo.save(b);
    }
}
