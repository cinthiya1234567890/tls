import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './vital.reducer';
import { IVital } from 'app/shared/model/vital.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVitalDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const VitalDetail = (props: IVitalDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { vitalEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Vital [<b>{vitalEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="vitalName">Vital Name</span>
          </dt>
          <dd>{vitalEntity.vitalName}</dd>
          <dt>
            <span id="vitalValue">Vital Value</span>
          </dt>
          <dd>{vitalEntity.vitalValue}</dd>
        </dl>
        <Button tag={Link} to="/vital" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/vital/${vitalEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ vital }: IRootState) => ({
  vitalEntity: vital.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(VitalDetail);
