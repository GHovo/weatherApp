package varmtech.com.weatherapp.databases.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import varmtech.com.weatherapp.databases.dao.EmployeeDao;
import varmtech.com.weatherapp.databases.entity.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract EmployeeDao employeeDao();
}
