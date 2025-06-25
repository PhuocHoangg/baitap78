package com.example.bai11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class SongController {
    @Autowired
    SongRepository songRepository;
    @Autowired
    ArtistRepository artistRepository;
 @GetMapping("/songs")
    public String songs(Model model) {
     model.addAttribute("songs", songRepository.findAll());
     return "songs";
 }
    @GetMapping("/add")
    public String add(Model model) {
     Song song=new Song();
     model.addAttribute("song", song);
     return "add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("song") Song song,
    @RequestParam String Artistname) {
     Artist artist= artistRepository.findByName(Artistname);
     if(artist==null) {
         artist=new Artist();
         artist.setName(Artistname);
         artistRepository.save(artist);
     }
     song.setArtist(artist);
     songRepository.save(song);
     return "redirect:/songs";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable int id, Model model) {
        Song song = songRepository.findById(id).orElse(null);
        SongDTO dto = new SongDTO();
        dto.setId(song.getId());
        dto.setTitle(song.getTitle());
        dto.setType(song.getType());
        dto.setArtist(song.getArtist().getName());

        model.addAttribute("songDTO", dto);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("songDTO") SongDTO songDTO) {
        Song song = songRepository.findById(songDTO.getId()).orElse(null);
        if (song == null) return "redirect:/songs";

        Artist artist = artistRepository.findByName(songDTO.getArtist());
        if (artist == null) {
            artist = new Artist();
            artist.setName(songDTO.getArtist());
            artistRepository.save(artist);
        }

        song.setTitle(songDTO.getTitle());
        song.setType(songDTO.getType());
        song.setArtist(artist);

        songRepository.save(song);
        return "redirect:/songs";
    }



}
