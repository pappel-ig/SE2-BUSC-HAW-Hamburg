package de.haw_hamburg.mensamatch.adapter.persistence;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import de.haw_hamburg.mensamatch.adapter.persistence.model.MealDao;
import de.haw_hamburg.mensamatch.domain.MealRepository;
import de.haw_hamburg.mensamatch.domain.model.Meal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@Repository
@RequiredArgsConstructor
public class MongoMealRepository implements MealRepository {

    private final MongoCollection<MealDao> collection;

    @Override
    public void store(Meal meal) {
        if (!(collection.countDocuments(and(eq("category", meal.getCategory()), eq("day", LocalDate.now()))) > 0)) {
            collection.insertOne(MealDao.from(meal));
        }
    }

    @Override
    public List<Meal> getFrom(LocalDate date) {
        List<Meal> meals = new ArrayList<>();
        final FindIterable<MealDao> day = collection.find(eq("day", date), MealDao.class);
        for (MealDao meal : day) {
            meals.add(meal.toMeal());
        }
        return meals;
    }
}
