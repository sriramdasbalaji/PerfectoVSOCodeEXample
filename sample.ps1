param (
    [string]$connectedServiceName,
    [string]$fromLib,
    [string]$ToLib
  )

Write-Verbose 'Entering sample.ps1'
Write-Verbose "fromLib = $fromLib"
Write-Verbose "ToLib = $ToLib"

# Import the Task.Common dll that has all the cmdlets we need for Build
import-module "Microsoft.TeamFoundation.DistributedTask.Task.Common"

if(!$fromLib)
{
    throw (Get-LocalizedString -Key "Working directory parameter is not set")
}

if(!(Test-Path $fromLib -PathType Container))
{
    throw ("$fromLib does not exist");
}

Write-Verbose "Setting working directory to $fromLib"
Set-Location $fromLib

Write-Host $fromLib
