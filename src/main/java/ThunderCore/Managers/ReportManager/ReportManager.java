package ThunderCore.Managers.ReportManager;

import ThunderCore.Managers.FileManager.FileManager;
import ThunderCore.Managers.ThunderManager;
import ThunderCore.ThunderCore;
import com.google.gson.Gson;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ReportManager implements ThunderManager {

    private ArrayList<ReportRecord> reports;
    private static ReportManager reportManager;
    public static ReportManager get() { return reportManager; }
    private final Gson gson = new Gson();


    public ReportManager() {
        reportManager = this;
        reports = new ArrayList<>();
    }

    public void createReport(Player reporter, Player reported, String reason, UUID id) {
        reports.add(new ReportRecord(reporter, reported, reason, id));
    }

    public void removeReport(ReportRecord report) { reports.remove(report); }

    public ArrayList<ReportRecord> getReports() { return reports; }

    public ReportRecord getReportByReported(Player player) {
        if (!reports.isEmpty()) {
            for (ReportRecord reportRecord : reports) {
                if (reportRecord.reported().equals(player)) {
                    return reportRecord;
                }
            }
        }
        return null;
    }

    public ReportRecord getReportByReporter(Player player) {
        if (!reports.isEmpty()) {
            for (ReportRecord reportRecord : reports) {
                if (reportRecord.reporter().equals(player)) {
                    return reportRecord;
                }
            }
        }
        return null;
    }

    public ReportRecord getReportByID(UUID id) {
        if (!reports.isEmpty()) {
            for (ReportRecord reportRecord : reports) {
                if (reportRecord.id().equals(id)) {
                    return reportRecord;
                }
            }
        }
        return null;
    }



    @Override
    public void load() {
        try {
            File folder = new File("ThunderCore/Reports/");
            File[] listOfFiles = folder.listFiles();
            for (File file : Objects.requireNonNull(listOfFiles)) {
                String fileContent = FileManager.readFile(file);
                reports.add(gson.fromJson(fileContent, ReportRecord.class));
            }
            reports = gson.fromJson(Objects.requireNonNull(FileManager.readFile(new File("Reports.json"))), reports.getClass());
        } catch(NullPointerException e) {
            ThunderCore.get().yellowMsg("There are no report files!");
        }
        ThunderCore.get().greenMsg("Reports loaded!");
    }

    @Override
    public void save() {
        for (ReportRecord report : reports) {
            String id = report.id().toString();
            FileManager.writeFile(new File("ThunderCore/Reports/Reports.json"), gson.toJson(report));
        }
        ThunderCore.get().greenMsg("Saved Reports!");
    }
}
