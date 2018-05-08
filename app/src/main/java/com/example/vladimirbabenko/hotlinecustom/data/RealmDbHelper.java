package com.example.vladimirbabenko.hotlinecustom.data;

import com.example.vladimirbabenko.hotlinecustom.entity.BascketItem;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;

public class RealmDbHelper {

  private final RealmConfiguration mConfiguration;

  public RealmDbHelper() {
    mConfiguration = new RealmConfiguration.Builder().name("com.babenkodev.db")
        //new version
        .schemaVersion(1).migration(new RealmMigrations()).build();
    Realm.setDefaultConfiguration(mConfiguration);
  }

  public <T extends RealmObject> void save(T object) {

    Realm realm = Realm.getInstance(mConfiguration);
    realm.beginTransaction();
    realm.copyToRealmOrUpdate(object);
    realm.commitTransaction();
  }

  public <T extends RealmObject> void saveAll(List<T> list) {
    Realm realm = Realm.getInstance(mConfiguration);
    realm.beginTransaction();
    realm.copyToRealmOrUpdate(list);
    realm.commitTransaction();
  }

  // Метод удаляет элемент из таблицы по id
  public <T extends RealmObject> void deleteElementById(Class<T> clazz, int id) {
    Realm realm = Realm.getInstance(mConfiguration);
    RealmResults<T> results = realm.where(clazz).equalTo("id", id).findAll();
    realm.beginTransaction();
    results.deleteAllFromRealm();
    realm.commitTransaction();
  }

  public <T extends RealmObject> List<T> getAll(Class<T> clazz) {
    List<T> list = new ArrayList<T>();
    Realm realm = Realm.getInstance(mConfiguration);
    realm.beginTransaction();
    list = realm.where(clazz).findAll();
    realm.commitTransaction();
    return list;
  }

  // Метод возвращает элемент по id. Принимает на вход класс объекта и шв, по которому он будет браться из бд
  public <T extends RealmObject> T getElementById(Class<T> clazz, int id) {

    Realm realm = Realm.getInstance(mConfiguration);
    RealmQuery<T> query = realm.where(clazz).equalTo("id", id);

    T t;
    try {
      t = clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }

    realm.beginTransaction();
    t = query.findFirst();
    realm.commitTransaction();
    return t;
  }

  // Метод делает updateValueNum

  public <T extends RealmObject> void updateValueNum(Class<T> clazz, int id, int value) {

    Realm realm = Realm.getInstance(mConfiguration);
    realm.executeTransaction(new Realm.Transaction() {
      @Override public void execute(Realm realm) {
        T t = realm.where(clazz).equalTo("id", id).findFirst();
        ((BascketItem) t).setNum(((BascketItem) t).getNum() + value);
        realm.insertOrUpdate(t);
      }
    });
  }

  // Метод возвращает списох объектов по id
  public <T extends RealmObject> List<T> getElementsFromDBByQuery(Class<T> clazz, String field,
      String value) {

    Realm realm = Realm.getInstance(mConfiguration);
    RealmQuery<T> query = realm.where(clazz).equalTo(field, value);

    List<T> list = new ArrayList<T>();
    realm.beginTransaction();
    list = query.findAll();
    realm.commitTransaction();
    return list;
  }

  // метод удаляет таблицу из базы данных
  public <T extends RealmObject> void dropRealmTable(Class<T> clazz) {
    RealmResults<T> results = Realm.getInstance(mConfiguration).where(clazz).findAll();

    // All changes to data must happen in a transaction
    Realm.getInstance(mConfiguration).beginTransaction();

    // Delete all matches
    results.deleteAllFromRealm();

    Realm.getInstance(mConfiguration).commitTransaction();
    Realm.getInstance(mConfiguration).close();
    //Timber.e("clearRealm " + clazz.toString());
  }
}

