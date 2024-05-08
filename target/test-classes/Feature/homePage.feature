Feature: Validate elements visibility and click ability on the LexisNexis Risk homepage

  Scenario: Selecting Financial Services Industry
    Given I open "https://risk.lexisnexis.co.uk/"
    And I accept cookies if present
    Then I should see the following industries under Choose Your Industry:
      | Financial Services     |
      | Insurance              |
      | Life and Pensions      |
      | Corporations and Non-Profits |
    When I click on "Financial Services"
    Then I should see the following options:
      | Financial Crime Compliance |
      | Customer Data Management   |
      | Collections & Recovery
      | Risk Orchestration         |
      | Fraud and Identity Management |
      | Credit Risk Assessment     |
      | Investigations and Due Diligence |
