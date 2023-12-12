# lifting-competition-tracker-service
<ol>
  <li>API Controller will accept a request from an outside application</li>
    <ol>
      <li>Will pull from domain models to provide schemas where needed</li>
    </ol> 
  <li>A call to the service layer will be made</li>
    <ol>
      <li>Service layer will call mapper to convert domain to entity model (where needed)</li>
    </ol> 
  <li>Service layer will call repository and pass in entity model for persisting</li>
  <li>Repository will echo back entity to validate persistence</li>
  <li> Service will convert entity back to domain and return to controller</li>
  <li> Controller will return entity along with HTTP status result</li>
</ol>