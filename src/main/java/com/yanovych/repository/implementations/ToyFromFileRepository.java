package com.yanovych.repository.implementations;

import com.google.gson.reflect.TypeToken;
import com.yanovych.entities.Child;
import com.yanovych.entities.Room;
import com.yanovych.entities.Toy;
import com.yanovych.helpers.ObjectFileReader;
import com.yanovych.helpers.ObjectFileWriter;
import com.yanovych.repository.interfaces.ToyRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ToyFromFileRepository implements ToyRepository {
    private final ObjectFileReader<Toy> reader;
    private final ObjectFileWriter<Toy> writer;
    private List<Toy> toys;
    private static ToyFromFileRepository instance = null;

    private ToyFromFileRepository() {
        this.reader = new ObjectFileReader<>();
        this.writer = new ObjectFileWriter<>();
        this.toys = this.getAllToys();
    }

    public static ToyFromFileRepository getInstance() {
        if (instance == null) {
            instance = new ToyFromFileRepository();
        }
        return instance;
    }

    @Override
    public Toy getToyById(Long id) {
        return getAllToys().stream()
                .filter(toy -> Objects.equals(toy.getId(), id)).findAny().orElse(null);
    }

    @Override
    public List<Toy> getAllToys() {
        return reader.readListOfObjects("toys.json", new TypeToken<ArrayList<Toy>>(){}.getType());
    }

    @Override
    public void addToy(Toy toy) {
        if (this.toys == null || this.toys.isEmpty()) {
            this.toys = new ArrayList<>();
            toy.setId(1L);
        } else {
            Long lastToyId = this.toys.stream()
                    .max(Comparator.comparingLong(Toy::getId)).get().getId();
            toy.setId(++lastToyId);
        }
        this.toys.add(toy);
        this.writer.writeListOfObjects("toys.json", this.toys, false);
    }

    @Override
    public void addToyToRoom(Toy toy, Room room) {
        toy.setToyRoomId(room.getId());
        this.updateToy(toy);
    }

    @Override
    public void updateToy(Toy toy) {
        for (int i = 0; i < this.toys.size(); i++) {
            if (toy.getId().equals(this.toys.get(i).getId())) {
                this.toys.set(i, toy);
                this.writer.writeListOfObjects("toys.json", this.toys, false);
                break;
            }
        }
    }
}
