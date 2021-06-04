package com.alura.agenda.database.dao;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

public class AgendaMigratins {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    };
    private static final Migration MCRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            //Nova tabela
            database.execSQL("CREATE TABLE IF NOT EXISTS `AlunoNovo` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT)");

            //Copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO AlunoNovo (id, nome, telefone, email)" +
                    "SELECT id, nome, telefone, email FROM Aluno ");

            //Remover a tabela antiga
            database.execSQL("DROP TABLE Aluno");

            //Renomear a tabela nova com o nome da antiga
            database.execSQL("ALTER TABLE AlunoNovo RENAME TO Aluno");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN `momentoDeCadastro` INTEGER");
        }
    };
    public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MCRATION_2_3, MIGRATION_2_3};
}
