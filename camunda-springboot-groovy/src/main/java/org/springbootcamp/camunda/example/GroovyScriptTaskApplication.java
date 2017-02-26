package org.springbootcamp.camunda.example;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.ProcessApplicationStartedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import static org.camunda.bpm.engine.variable.Variables.putValue;

@SpringBootApplication
@EnableProcessApplication
public class GroovyScriptTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(GroovyScriptTaskApplication.class, args);
  }

  private final Logger logger = LoggerFactory.getLogger(GroovyScriptTaskApplication.class);

  @Autowired
  private RuntimeService runtimeService;

  @Autowired
  private HistoryService historyService;

  @EventListener
  public void onStart(final ProcessApplicationStartedEvent event) {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ScriptTask",
      putValue("a", 5)
        .putValue("b", 5));

    logger.info("start instance: {}", processInstance.getId());
  }
}
