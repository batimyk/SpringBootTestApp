package ua.batimyk.springboot.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by N on 02-Oct-16.
 * SpringBootTestApp
 */
@Entity
@Table(name = "features",
        uniqueConstraints =
        @UniqueConstraint(columnNames =
                         {"rendering_engine"
                        , "browser"
                        , "platforms"
                        , "engine_version"
                        , "css_grade"})
)
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rendering_engine")
    @NotNull
    @Size(max = 20)
    private String renderingEngine;

    @Column(name = "browser")
    @NotNull
    @Size(max = 30)
    private String browser;

    @Column(name = "platforms")
    @Size(max = 100)
    private String platforms;

    @Column(name = "engine_version")
    @Size(max = 5)
    private String engineVersion;

    @Column(name = "css_grade")
    @Size(max = 5)
    private String cssGrade;

    public Feature(String renderingEngine
            , String browser
            , String platforms
            , String engineVersion
            , String cssGrade) {
        this.renderingEngine = renderingEngine;
        this.browser = browser;
        this.platforms = platforms;
        this.engineVersion = engineVersion;
        this.cssGrade = cssGrade;
    }

    public Feature() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRenderingEngine() {
        return renderingEngine;
    }

    public void setRenderingEngine(String renderingEngine) {
        this.renderingEngine = renderingEngine;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public String getCssGrade() {
        return cssGrade;
    }

    public void setCssGrade(String cssGrade) {
        this.cssGrade = cssGrade;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", renderingEngine='" + renderingEngine + '\'' +
                ", browser='" + browser + '\'' +
                ", platforms='" + platforms + '\'' +
                ", engineVersion='" + engineVersion + '\'' +
                ", cssGrade='" + cssGrade + '\'' +
                '}';
    }
}
