package ua.batimyk.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;
import ua.batimyk.springboot.model.Feature;
import ua.batimyk.springboot.model.FeatureDAO;


import java.util.List;


/**
 * Created by N on 30-Sep-16.
 * SpringBootTestApp
 */

@RestController
public class FeatureController {


    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Feature> getById(@PathVariable("id") long id) {
        Feature feature = featureDAO.getById(id);
        if (feature == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feature, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Feature>> getAll() {
        List<Feature> features = featureDAO.getAll();

        if (features.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(features, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Feature> delete(@PathVariable("id") long id) {
        Feature feature = featureDAO.getById(id);
        if (feature == null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        featureDAO.delete(feature);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/feature/", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Feature feature, UriComponentsBuilder ucBuilder) {

        try {
            featureDAO.create(feature);
        } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(feature.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/feature/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Feature> update(@PathVariable("id") long id, @RequestBody Feature featureParam) {

        Feature feature = featureDAO.getById(id);

        if (feature == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        feature.setBrowser(feature.getBrowser());
        feature.setEngineVersion(featureParam.getEngineVersion());
        feature.setCssGrade(featureParam.getCssGrade());
        feature.setPlatforms(featureParam.getPlatforms());
        feature.setRenderingEngine(featureParam.getRenderingEngine());

        try {
            featureDAO.update(feature);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(feature, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByRenderingEngine", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Object>> getByRenderingEngine() {
        List<Object> byRenderingEngine = featureDAO.getByRenderingEngine();

        if (byRenderingEngine.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(byRenderingEngine, HttpStatus.OK);
    }

    @Autowired
    private FeatureDAO featureDAO;
}
